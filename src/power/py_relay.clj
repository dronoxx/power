(ns
  ^{:doc    "Power relay device that uses python script"
    :author "Santiago de Pedro"
    :added  "1.0"}
  power.py-relay
  (:require [clojure.java.shell :refer [sh]]
            [clojure.java.io :as io])
  (:import (power.device Device)))

(def ^:private python-path "/usr/bin/python")
(def ^:private device-resource "relay.py")
(def ^:private destination-resource-dir "/tmp/rpi/devices/")

(defn- resource-content
  "Return the resource content"
  ^{:author "Santiago de Pedro"
    :added  "1.0"}
  [resource]
  (slurp (io/resource resource)))

(defn- create-resource-file
  "Create a temporal python script using the relay resource"
  ^{:author "Santiago de Pedro"
    :added  "1.0"}
  [dest-file content]
  (let [temp-file (io/file (str destination-resource-dir dest-file))]
    (do
      (io/make-parents temp-file)
      (spit temp-file content)
      temp-file)))

(defn- cleanup-resource-file
  "Remove the python script"
  ^{:author "Santiago de Pedro"
    :added  "1.0"}
  [resource-file]
  (io/delete-file resource-file true))

(defn- do-command
  "Send a command to relay using python script"
  ^{:author "Santiago de Pedro"
    :added  "1.0"}
  [driver-file device-pin command]
  (let [option (-> command name .toUpperCase)
        py-resource (-> driver-file io/file .getPath)]
    (sh python-path py-resource (str device-pin) option)
    true))

; Relay device
; Implements Device protocol
(deftype Relay [driver-file device-pin]
  Device
  (close [this] true)
  (transmit [this command] (do-command driver-file device-pin command)))

(defn make-relay-device
  "Create a relay device"
  ^{:author "Santiago de Pedro"
    :added  "1.0"}
  [device-pin]
  (if-let [dev-resource-content (resource-content device-resource)]
    (let [driver-file (create-resource-file device-resource dev-resource-content)]
      (Relay. driver-file device-pin))))

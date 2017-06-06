(ns power.py-relay
  (:require [clojure.java.shell :refer [sh]]
            [clojure.java.io :as io])
  (:import (power.device Device)))

(def ^:private python-path "/usr/bin/python")
(def ^:private device-resource "relay.py")
(def ^:private destination-resource-dir "/tmp/rpi/devices/")

(def ^:private commands {:on  :high
                         :off :low})

(defn- resource-content
  [resource]
  (slurp (io/resource resource)))

(defn- create-resource-file
  [dest-file content]
  (let [temp-file (io/file (str destination-resource-dir dest-file))]
    (do
      (io/make-parents temp-file)
      (spit temp-file content)
      temp-file)))

(defn- cleanup-resource-file
  [resource-file]
  (io/delete-file resource-file true))

(defn- do-command
  [driver-file command]
  (let [option (-> commands command name .toUpperCase)
        py-resource (-> driver-file io/file .getPath)]
    (sh python-path py-resource option)
    true))

(deftype Relay [driver-file]
  Device
  (close [this] (cleanup-resource-file driver-file))
  (transmit [this command] (do-command driver-file command)))

(defn make-relay-device
  []
  (if-let [dev-resource-content (resource-content device-resource)]
    (let [driver-file (create-resource-file device-resource dev-resource-content)]
      (Relay. driver-file))))
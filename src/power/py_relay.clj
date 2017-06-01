(ns power.py-relay
  (:require [clojure.java.shell :refer [sh]]
            [clojure.java.io :as io])
  (:import (power.device Device)))

(def ^:private python-path "/usr/bin/python")
(def ^:private python-file "relay.py")

(def ^:private commands {:on  :high
                         :off :low
                         :close :close})

(defn- do-command
  [command]
  (let [option (-> commands command name .toUpperCase)
        py-resource (-> python-file io/resource .getPath)]
    (sh python-path py-resource option)))

(deftype Relay []
  Device
  (close [this] (do-command :close))
  (transmit [this command] (do-command command)))

(defn make-relay-device
  []
  Relay.)

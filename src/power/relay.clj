(ns power.relay
  (:require [gpio.core :refer [open-port set-direction! write-value! close!]])
  (:import (power.device Device)))

(def ^:private commands {:on  :high
                         :off :low})

(defn- make-gpio-port
  [pin]
  (if-let [port (open-port pin)]
    (do
      (set-direction! port :out)
      port)))

(defn- do-command
  [port command]
  (try
    (do
      (write-value! port (command commands))
      true)
    (catch Exception e false)))


(deftype Relay [port]
  Device
  (close [this] (close! port))
  (transmit [this command] (do-command port command)))

(defn make-relay-device
  [pin]
  (let [relay-port (make-gpio-port pin)]
    (-> relay-port Relay.)))
(ns power.relay
  (:import (power.device Device)))

(deftype Relay [pin]
  Device
  (close [this])
  (transmit [this command] command))

(defn make-relay-device
  [pin]
  (let [pin-number pin]
    (-> pin-number Relay. connect)))
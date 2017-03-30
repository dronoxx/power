(ns power.device
  (:import (java.io Closeable)))


(defprotocol Device
  (close [this])
  (transmit [this command]))

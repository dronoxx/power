(ns
  ^{:doc    "Power core with main functionality"
    :author "Santiago de Pedro"
    :added  "1.0"}
  power.core
  (:require [power.device :refer [transmit]]))

(def ^:dynamic *device* nil)

(def interval-values
  {
   :second 1
   :minute 60
   :hour   3600})

(defn- time-to-wait
  "Time to wait before send commands to power device"
  ^{:author "Santiago de Pedro"
    :added  "1.0"}
  [time interval]
  (* 1000 time (interval interval-values)))

(defmacro with-device
  "Use a device to perform commands with it"
  ^{:author "Santiago de Pedro"
    :added  "1.0"}
  [device & body]
  `(binding [*device* ~device]
     (with-open [device# *device*]
       ~@body)))

(defn power
  "Send command to power device"
  ^{:author "Santiago de Pedro"
    :added  "1.0"}
  ([command]
   (when-let [device *device*]
     (transmit device command)))
  ([command time interval]
   (let [time-to-wait (time-to-wait time interval)
         command (future
                   (Thread/sleep time-to-wait)
                   (power command))]
     @command)))



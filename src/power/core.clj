(ns power.core
  (:require [power.device :refer [transmit]]))

(def ^:dynamic *device* nil)

(def interval-values
  {
   :second 1
   :minute 60
   :hour   3600})

(defn- time-to-wait
  [time interval]
  (* 1000 time (interval interval-values)))

(defmacro with-device
  [device & body]
  `(binding [*device* ~device]
     (with-open [device# *device*]
       ~@body)))

(defn power
  ([command]
   (when-let [device *device*]
     (transmit device command)))
  ([command time interval]
   (let [time-to-wait (time-to-wait time interval)
         command (future
                   (Thread/sleep time-to-wait)
                   (power command))]
     @command)))



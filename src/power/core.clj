(ns power.core
  (:require [power.device :refer [transmit]]))

(def ^:dynamic *device* nil)

(def interval-values
  {
   :second 1
   :minute 60
   :hour   3600})

(defn- wait
  [time interval]
  (Thread/sleep (* 1000 time (interval interval-values))))

(defmacro with-device
  [device & body]
  `(binding [*device* ~device]
     (with-open [device# *device*]
       ~@body)))

(defn power
  [command time interval]
  {:pre [(not (nil? *device*)) (contains? (keys interval-values) interval)]}
  (when-let [device *device*]
    (future
      (wait time interval)
      (transmit device command))))

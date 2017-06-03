(ns power.main
  (:require [power.core :refer :all]
            [power.clj-relay :refer :all])
  (:gen-class))

(def device-pin 16)

(defn -main [& args]
  (let [device (make-relay-device device-pin)]
    (with-device device
                 (power (keyword (first args)) 5 :second))))
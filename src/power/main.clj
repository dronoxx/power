(ns power.main
  (:require [power.core :refer :all]
            [power.py-relay :refer :all])
  (:gen-class))

(defn -main [& args]
  (let [device (make-relay-device)]
    (with-device device
                 (power (keyword (first args)) 5 :second))))
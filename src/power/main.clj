(ns power.main
  (:require [power.core :refer :all]
            [power.relay-py :refer :all])
  (:gen-class))

(def led-pin 16)
(def led-device (make-relay-device led-pin))


(defn -main [& args]
  (with-device led-device
               (power :on 1 :second)))

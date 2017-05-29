(ns power.core-test
  (:require [clojure.test :refer :all]
            [power.core :refer :all]
            [power.device :refer :all]
            [power.relay :refer :all])
  (:gen-class))


(def led-pin 16)
(def led-device (make-relay-device led-pin))


(defn -main [& args]
  (with-device led-device
               (power :on 1 :second)))


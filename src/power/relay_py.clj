(ns power.relay-py
  (:require [clojure.java.shell :only [sh]]
            [clojure.java.io :as io]))

(def python-path "/usr/bin/python")
(def python-file "relay.py")

(defn transmit
  [option]
  (let [option (-> (str option) .toUpperCase)
        py-resource (-> python-file io/resource .getPath)]
    (sh python-path py-resource option)))

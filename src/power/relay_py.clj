(ns power.relay-py
  (:require [clojure.java.shell :refer [sh]]
            [clojure.java.io :as io]))

(def python-path "/usr/bin/python")
(def python-file "relay.py")

(defn transmit
  [option]
  (let [option (-> (name option) .toUpperCase)
        py-resource (-> python-file io/resource .getPath)]
    (sh python-path py-resource option)))

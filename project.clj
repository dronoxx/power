(defproject power "0.1.0-SNAPSHOT"
  :description "Library to power devices in rpi using clojure"
  :url "https://github.com/ieer/power"
  :license {:name "Eclipse Public License"
            :url "https://github.com/ieer/power/blob/master/LICENSE"}
  :repl-options {:timeout 120000}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-gpio "0.2.0"]])

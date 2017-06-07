(defproject power "0.1.0"
  :description "Library to power devices in rpi using clojure"
  :url "https://github.com/ieer/power"
  :license {:name "Eclipse Public License"
            :url "https://github.com/ieer/power/blob/master/LICENSE"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :repositories [["releases" {:url           "https://clojars.org/repo"
                              :username      :env/CLOJAR_USERNAME
                              :password      :env/CLOJAR_PASSWORD
                              :sign-releases false}]])

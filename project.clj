(defproject btcj "0.1.0-SNAPSHOT"
  :description "A BitTorrent Client implemented in Clojure"
  :url "http://github.com/pietromenna/btcj"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.cli "0.3.1"]]
  :profiles {:dev {;:global-vars {*warn-on-reflection* }
                   :dependencies [[midje "1.6.3"  :exclusions [org.clojure/clojure]]
                                  [org.clojars.runa/conjure "2.2.0"]
                                  [clj-http "1.1.2"]
                                  [ring/ring-codec "1.0.0"]]}}
  :plugins [[lein-midje "3.1.3"]])
(ns btcj.bdecoding
  (:require [clojure.java.io :as io]
            [btcj.bencoding :refer :all]))

(defn bdecode-stream [stream]
  (cond 
    (= (first stream) dict-begin-delimiter) (hash-map)
    (= (first stream) list-begin-delimiter) (vector)
    (= (first stream) int-begin-delimiter) 3
    ))
(ns btcj.bdecoding
  (:require [clojure.java.io :as io]
            [btcj.bencoding :refer :all]))

(defn bdecode-stream [] )

(defn bdecode-dict [stream] 
  (hash-map))

(defn bdecode-list [stream] 
  (vector))

(defn bdecode-int [stream] 
  (let [first-ocurrence (apply str (re-seq #"\bi[0-9]+e" stream))
        length-int-stream (count first-ocurrence)]
    (if (= length-int-stream (count stream))
      (Integer. (apply str (drop 1 (drop-last 1 first-ocurrence))))
      (bdecode-stream 
        (Integer. (apply str (drop 1 (drop-last 1 first-ocurrence))))
        (apply str (drop length-int-stream stream))))))

(defn bdecode-stream
  ([stream]
   (cond 
     (= (first stream) dict-begin-delimiter) (bdecode-dict stream)
     (= (first stream) list-begin-delimiter) (bdecode-list stream)
     (= (first stream) int-begin-delimiter) (bdecode-int stream)
     (Character/isDigit (first stream)) ""))
  ([already-decoded stream]
   (if (coll? already-decoded)
     (conj already-decoded (bdecode-stream stream))
     (list already-decoded (bdecode-stream stream)))))
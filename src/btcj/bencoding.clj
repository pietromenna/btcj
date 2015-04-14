(ns btcj.bencoding)

(def string-separator ":" )

(defn bencode-string [input-string] 
  (str (count input-string) string-separator input-string))

(defn bdecode-string [encoded-string] ()))

(defn bencode-int [input-int] ())

(defn bdecode-int [encoded-int] ())

(defn bencode-list [input-list] ())

(defn bdecode-list [encoded-list] ())

(defn bencode-dict [input-dict] ())

(defn bdecode-dict [encoded-dict] ())
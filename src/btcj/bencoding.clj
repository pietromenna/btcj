(ns btcj.bencoding)

(def string-delimiter ":" )

(def int-begin-delimiter "i" )

(def int-end-delimiter "e" )

(defn bencode-string [input-string] 
  (str (count input-string) string-delimiter input-string))

(defn bdecode-string [encoded-string] ())

(defn bencode-int [input-int] 
  (str int-begin-delimiter input-int int-end-delimiter))

(defn bdecode-int [encoded-int] ())

(defn bencode-list [input-list] ())

(defn bdecode-list [encoded-list] ())

(defn bencode-dict [input-dict] ())

(defn bdecode-dict [encoded-dict] ())
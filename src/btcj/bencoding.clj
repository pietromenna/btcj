(ns btcj.bencoding)

(def string-delimiter ":" )

(def int-begin-delimiter "i" )

(def list-begin-delimiter "l" )

(def dict-begin-delimiter "d" )

(def common-end-delimiter "e" )

(defn bencode-string [input-string] 
  (str (count input-string) string-delimiter input-string))

(defn bdecode-string [encoded-string] ())

(defn bencode-int [input-int] 
  (str int-begin-delimiter input-int common-end-delimiter))

(defn bencode-commons [input]
  (cond 
    (integer? input) (bencode-int input)
    (string? input) (bencode-string input)
    )
  )

(defn bdecode-int [encoded-int] ())

(defn bencode-list [input-list] 
  (str list-begin-delimiter (apply str (map bencode-commons input-list)) common-end-delimiter))

(defn bdecode-list [encoded-list] ())

(defn bencode-dict [input-dict] ())

(defn bdecode-dict [encoded-dict] ())
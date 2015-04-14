(ns btcj.bencoding)

(def string-delimiter ":" )

(def int-begin-delimiter "i" )

(def list-begin-delimiter "l" )

(def dict-begin-delimiter "d" )

(def common-end-delimiter "e" )

(defn bencode-commons [input])

(defn bdecode-commons [input])

(defn bencode-string [input-string] 
  (str (count input-string) string-delimiter input-string))

(defn bdecode-string [encoded-string] 
  "" )

(defn bencode-int [input-int] 
  (str int-begin-delimiter input-int common-end-delimiter))

(defn bdecode-int [encoded-int] 
  (Integer. (apply str (drop 1 (drop-last 1 encoded-int)))))

(defn bencode-list [input-list] 
  (str list-begin-delimiter (apply str (map bencode-commons input-list)) common-end-delimiter))

(defn bdecode-list [encoded-list] ())

(defn bencode-commons [input]
  (cond 
    (vector? input) (bencode-list input)
    (integer? input) (bencode-int input)
    (string? input) (bencode-string input)
    )
  )

(defn bencode-dict [input-dict]
  (let [bencode-key-value (fn [x] 
                            (apply str (str (bencode-commons (key x)) (bencode-commons (val x))))) ] 
  (str dict-begin-delimiter (apply str (map bencode-key-value input-dict)) common-end-delimiter)))

(defn bdecode-dict [encoded-dict] ())

(defn bdecode-commons [input] 
  (cond 
    (= (get input 0) int-begin-delimiter) (bdecode-int input)
    (= (get input 0) list-begin-delimiter) (bdecode-list input)
    (= (get input 0) dict-begin-delimiter) (bdecode-dict input)
    :else (bdecode-string input)
    )
  )
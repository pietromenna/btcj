(ns btcj.bencoding)

(def string-delimiter ":" )

(def int-begin-delimiter \i )

(def list-begin-delimiter \l )

(def dict-begin-delimiter \d )

(def common-end-delimiter "e" )

(defn bencode-data [input])

(defn- bencode-string [input-string] 
  (str (count input-string) string-delimiter input-string))

(defn- bencode-int [input-int] 
  (str int-begin-delimiter input-int common-end-delimiter))

(defn- bencode-list [input-list] 
  (str list-begin-delimiter (apply str (map bencode-data input-list)) common-end-delimiter))

(defn- bencode-dict [input-dict]
  (let [bencode-key-value (fn [x] 
                            (apply str (str (bencode-data (key x)) (bencode-data (val x))))) ] 
  (str dict-begin-delimiter (apply str (map bencode-key-value input-dict)) common-end-delimiter)))

(defn bencode-data [input]
  (cond 
    (map? input) (bencode-dict input)
    (vector? input) (bencode-list input)
    (integer? input) (bencode-int input)
    (string? input) (bencode-string input)
    )
  )
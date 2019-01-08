(ns peruse.data
  (:require [clojure.core.protocols :as p]))

(extend-protocol p/Datafiable
  clojure.lang.PersistentArrayMap
  (datafy [m]
    (with-meta m (meta m))))

(extend-protocol p/Navigable
  clojure.lang.PersistentArrayMap
  (nav [coll k v]
      (with-meta (get coll k) (meta coll))))

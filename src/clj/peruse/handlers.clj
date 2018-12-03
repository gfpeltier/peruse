(ns peruse.handlers
  (:require [peruse.page :as page]
            [cognitect.transit :as transit]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log])
  (:import (java.io ByteArrayInputStream)))

(defn index
  []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body page/main-page})

;; TODO: need to be able to return body map
(defn handle-clj [{:keys [data] :as req-body}]
  (log/info "handling request...")
  (log/info req-body)
  (let [resp-data (-> data
                      read-string
                      eval)]
    (log/info "Response:" resp-data)
    {:status 200
     :headers {}
     :body {:data resp-data}}))
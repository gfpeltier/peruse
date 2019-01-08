(ns peruse.handlers
  (:require [peruse.page :as page]
            [peruse.data]
            [cognitect.transit :as transit]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [clojure.datafy :refer [datafy nav]])
  (:import (java.io ByteArrayInputStream)))

(defn index
  []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body page/main-page})

(defn handle-clj [{:keys [data] :as req-body}]
  (log/info "handling request...")
  (log/info req-body)
  (try
    (let [resp-data (-> data
                        read-string
                        eval
                        datafy)]
      (log/info "Response:" resp-data)
      {:status  200
       :headers {}
       :body    {:data resp-data}})
    (catch Exception e
      {:status  200
       :headers {}
       :body    {:data (str e)}})))
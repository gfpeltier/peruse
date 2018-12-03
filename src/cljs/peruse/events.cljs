(ns peruse.events
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [re-frame.core :as rf]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [peruse.db :as app-db]))


(rf/reg-event-db
  :init-db
  (fn [_ _] app-db/init-db))

(rf/reg-fx
  :http-req
  (fn [[req callback]]
    (print req)
    (go (let [resp (<! (http/post "http://localhost:3030/api"
                                  {:transit-params req}))]
          (when callback
            (callback resp))))))
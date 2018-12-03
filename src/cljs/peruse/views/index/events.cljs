(ns peruse.views.index.events
  (:require [re-frame.core :as rf]
            [cognitect.transit :as t]
            [peruse.events]))


(rf/reg-event-db
  :index-append-output
  (fn [db [_ data]] (update db :output conj data)))

(rf/reg-event-fx
  :send-clj
  (fn [_ [_ clj-str]]
    {:http-req [{:data clj-str}
                (fn [{:keys [body]}]
                  (let [resp (t/read (t/reader :json) body)]
                    (rf/dispatch [:index-append-output (:data resp)])))]}))
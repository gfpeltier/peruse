(ns peruse.views.index.events
  (:require [re-frame.core :as rf]
            [cognitect.transit :as t]
            [peruse.events]))


(def custom-readers {"var"   (fn [[_ v]] v)
                     "class" (fn [c] c)
                     "ratio" (fn [[v1 v2]] (str v1 "/" v2))
                     "n"     (fn [v] v)})

(rf/reg-event-db
  :index-append-output
  (fn [db [_ data]] (update db :output conj data)))

(rf/reg-event-fx
  :send-clj
  (fn [_ [_ clj-str]]
    {:http-req [{:data clj-str}
                (fn [{:keys [body]}]
                  (let [resp (t/read (t/reader :json {:handlers custom-readers}) body)]
                    (rf/dispatch [:index-append-output (:data resp)])))]}))
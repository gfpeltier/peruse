(ns peruse.views.index.subs
  (:require [re-frame.core :as rf]))


(rf/reg-sub
  :index-output
  (fn [db _] (:output db)))

(ns peruse.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [peruse.router :as router]
            [peruse.events]
            [peruse.subs]))

(defn current-page []
  (let [page @(rf/subscribe [:current-page])]
    [(router/views page)]))

(defn mount-target []
  (r/render [current-page] (.getElementById js/document "mount-target")))

(defn main! []
  (rf/dispatch-sync [:init-db])
  (mount-target))

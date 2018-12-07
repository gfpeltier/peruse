(ns peruse.components.golden-layout
  (:require [reagent.core :as r]
            [cljsjs.jquery]
            [cljsjs.golden-layout]))


(defn component [id content]
  (r/create-class {:component-did-mount
                   (fn [_]
                     )

                   :reagent-render
                   (fn [id content]
                     [:div {:id id}])}))
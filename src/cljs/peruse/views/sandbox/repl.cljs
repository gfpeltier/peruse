(ns peruse.views.sandbox.repl
  (:require [reagent.core :as r]
            [peruse.components.clj-input :as clj-in]))


(def component (r/create-class
                 {:reagent-render
                  (fn [_] [:div [clj-in/component]])}))
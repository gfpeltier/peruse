(ns peruse.router
  (:require [peruse.views.index.view :as index]
            [peruse.views.sandbox.view :as sandbox]))

(def views {:index index/component
            :sandbox sandbox/component})
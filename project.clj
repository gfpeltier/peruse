(defproject peruse "0.1.0-SNAPSHOT"
  :description "FOSS version of REBL"
  :url "https://github.com/grantpeltier/peruse"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.cognitect/transit-clj "0.8.313"]
                 [org.clojure/data.json "0.2.6"]
                 [environ "1.1.0"]
                 [org.immutant/immutant "2.1.10"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring-transit "0.1.6"]
                 [ring/ring-json "0.4.0"]

                 ;; CLJS Deps
                 [org.clojure/clojurescript "1.10.238" :scope "provided"]
                 [cljs-http "0.1.45"]
                 [com.cognitect/transit-cljs "0.8.239"]
                 [hiccup "1.0.5"]
                 [reagent "0.6.2"]
                 [re-frame "0.9.4"]
                 [cljsjs/golden-layout "1.5.9-0"]
                 [cljsjs/ace "1.4.2-0"]]

  :plugins [[lein-jsass "0.2.1"]
            [lein-cljsbuild "1.1.5"]
            [lein-environ "1.1.0"]]

  :source-paths ["src/clj" "src/cljc"]

  :main peruse.core

  :jsass {:source "resources/scss"
          :target "resources/public/css"}

  :env {:app-version :project/version}

  :cljsbuild {:builds {:dev
                       {:source-paths ["src/cljc" "src/cljs" "env/dev/cljs"]
                        :compiler {:main          peruse.dev
                                   :output-to     "target/cljs/public/js/app.js"
                                   :output-dir    "target/cljs/public/js/dev"
                                   ; Asset path must be this to reach compiled resources!
                                   :asset-path    "js/dev"
                                   :optimizations :none
                                   :pretty-print  true}}}}

  :profiles {:dev {:plugins [[lein-figwheel "0.5.16"]]
                   :dependencies [[org.clojure/tools.nrepl "0.2.13"]
                                  [com.cemerick/piggieback "0.2.2-SNAPSHOT"]
                                  [figwheel-sidecar "0.5.16"]
                                  [re-frisk "0.5.4"]]
                   :env {:dev true}}}
  :figwheel {:http-server-root "resources/public"
             :server-port      3449
             :nrepl-port       7002
             :nrepl-middleware ["cemerick.piggieback/wrap-cljs-repl"]
             :css-dirs         ["resources/public/css"]})

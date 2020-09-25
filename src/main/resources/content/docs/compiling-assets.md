# Compiling Assets 
---
[TOC]

## Introduction

Severell uses Laravel Mix to compile assets. See initial documentation here
[Laravel Mix Docs](https://laravel-mix.com/docs/5.0/basic-example).

TailwindCSS is included by default, and the predefined views use TailwindCSS. There is already a 
minified CSS file included that contains the necessary TailwindCSS. Feel free to change this if
you wish. 

### Running Mix

Mix is a layer on top of Webpack that makes it easier to configure. To run Mix you
need to run an NPM command from `package.json`.

```bash
//Run Mix
npm run dev

//Run Mix and minify output - this will also run purgeCSS for tailwind.
npm run production
```

You can also have Mix watch your assets and rebuild when necessary. 

```bash
npm run watch
```

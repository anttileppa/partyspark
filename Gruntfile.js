/* jshint esversion: 6 */
/* global module:false */

const _ = require('lodash');
const fs = require('fs');
const util = require('util');
const path = require('path');

module.exports = function(grunt) {
  require('load-grunt-tasks')(grunt);
  
  grunt.initConfig({
    'sass': {
      dist: {
        options: {
          style: 'compressed'
        },
        files: [{
          expand: true,
          cwd: 'client/scss',
          src: ['*.scss'],
          dest: 'src/main/resources/public/css',
          ext: '.min.css'
        }]
      }
    },
    'babel': {
      options: {
        sourceMap: true,
        minified: true
      },
      dist: {
        files: [{
          expand: true,
          cwd: 'client/js',
          src: ['*.js'],
          dest: 'src/main/resources/public/js/',
          ext: '.min.js'
        }]
      }
    }
  });
  
  grunt.registerTask('default', [ 'sass', 'babel' ]);
};
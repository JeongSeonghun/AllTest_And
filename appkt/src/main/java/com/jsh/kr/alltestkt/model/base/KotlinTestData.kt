package com.jsh.kr.alltestkt.model.base

// data class
// use main(first) constructor value
// auto create -> equals(), hashCode(), toString, copy()
// https://tourspace.tistory.com/108
// first constructor
//data class KotlinTestData constructor(val dataVal: String, var dataVar: String) {
data class KotlinTestData (val dataVal: String, var dataVar: String) {
    constructor() : this("dataVal", "")
}
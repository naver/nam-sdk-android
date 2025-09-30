// Top-level build file where you can add configuration options common to all sub-projects/modules.
        plugins {
            id("com.android.application") version "8.8.0" apply false
            id("org.jetbrains.kotlin.android") version "2.1.10" apply false
            id("com.diffplug.spotless") version "6.7.2" apply true
        }

        // for the convention. you don't need to use this
        spotless {
            format("misc") {
                target("*.gradle", "*.md", ".gitignore")
                trimTrailingWhitespace()
                indentWithSpaces()
                endWithNewline()
            }
            java {
                target("**/*.java")
                removeUnusedImports()
                licenseHeader(
                    """
                    /*
                     * NAM(Naver Ad Manager) SDK for Android
                     * 
                     * Copyright 2022-present NAVER Corp.
                     * All rights reserved.
                     * 
                     * Unauthorized use, modification and redistribution of this software are strongly prohibited.
                     */
                    """.trimIndent()
                )
                googleJavaFormat().aosp()
            }
            kotlin {
                target("**/*.kt")
                licenseHeader(
                    """
                    /*
                     * NAM(Naver Ad Manager) SDK for Android
                     * 
                     * Copyright 2022-present NAVER Corp.
                     * All rights reserved.
                     * 
                     * Unauthorized use, modification and redistribution of this software are strongly prohibited.
                     */
                    """.trimIndent()
                )
                ktlint()
            }
        }
/*
 * Copyright to the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package examples.coffeemaker

import com.sun.jini.start.ServiceDescriptor
import org.rioproject.config.Component
import org.rioproject.security.SecureEnv
import sorcer.provider.boot.SorcerServiceDescriptor

/**
 * Configuration to start all arithmetic tester providers
 */
@Component("com.sun.jini.start")
class StartAll {

    StartAll() {
        SecureEnv.setup()
    }

    ServiceDescriptor[] getServiceDescriptors() {
        String riverVersion = System.getProperty("river.version")
        String sorcerVersion = System.getProperty("sorcer.version")
        String websterUrl = System.getProperty("webster.url")
        boolean useHttps = websterUrl.startsWith("https")
        String policy = System.getProperty("java.security.policy")

        String relativeRepoPath = System.getProperty("relative.repo.path")
        String projectBuildDir = System.getProperty("project.build.dir")
        String buildLibPath = "${projectBuildDir}/libs";
        String configPath = "${projectBuildDir}/../configs"
        String projectVersion = System.getProperty("project.version")

        def descriptors = []
        ["coffeemaker", "delivery"].each { provider ->
            def configArg = ["${configPath}/${provider}-prv.config"]
            def codebase = "${relativeRepoPath}/coffeemaker-${projectVersion}-dl.jar sorcer-dl-${sorcerVersion}.jar sorcer-ui-${sorcerVersion}.jar jsk-dl-${riverVersion}.jar"

            println "===> useHttps: $useHttps"
            descriptors << new SorcerServiceDescriptor(codebase,
                    policy,
                    "${buildLibPath}/coffeemaker-${projectVersion}-prv.jar",
                    "sorcer.core.provider.ServiceTasker",
                    useHttps,
                    configArg as String[])
        }
        return descriptors as ServiceDescriptor[]
    }
}
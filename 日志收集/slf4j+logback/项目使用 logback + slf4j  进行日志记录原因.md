java常用日志框架类别介绍
Log4j()
Log4j2(Log4j的升级产品)
Commons Logging(兼容不好)
Slf4j 类似于Commons Logging，是一套简易Java日志门面，本身并无日志的实现。（Simple Logging Facade for Java，缩写Slf4j）。
Logback（logback和log4j是同一个作者创作，它是log4j的升级版，slf4j阵营）
Jul (自Java1.4以来的官方日志实现)
--------------------------------------------------------------------------------------------
如果是在一个新的项目中建议使用Slf4j与Logback组合，这样有如下的几个优点。
Slf4j实现机制决定Slf4j限制较少，使用范围更广。由于Slf4j在编译期间，静态绑定本地的LOG库使得通用性要比Commons logging要好。
Logback拥有更好的性能。Logback声称：某些关键操作，比如判定是否记录一条日志语句的操作，其性能得到了显著的提高。这个操作在Logback中需要3纳秒，而在Log4J中则需要30纳秒。LogBack创建记录器（logger）的速度也更快：13毫秒，而在Log4J中需要23毫秒。更重要的是，它获取已存在的记录器只需94纳秒，而Log4J需要2234纳秒，时间减少到了1/23。跟JUL相比的性能提高也是显著的。
Commons Logging开销更高 在使Commons Logging时为了减少构建日志信息的开销，通常的做法是：
if(log.isDebugEnabled()){
log.debug("User name： " +
user.getName() + " buy goods id ：" + good.getId());
}
在Slf4j阵营，你只需这么做：
log.debug("User name：{} ,buy goods id ：{}", user.getName(),good.getId());
也就是说，slf4j把构建日志的开销放在了它确认需要显示这条日志之后，减少内存和cup的开销，使用占位符号，代码也更为简洁
Logback文档免费。Logback的所有文档是全面免费提供的，不象Log4J那样只提供部分免费文档而需要用户去购买付费文档。
-------------------------------------------------------------------------------------------------------------------------
Logback 和 log4j 是非常相似的，如果你对 log4j 很熟悉，那对 logback 很快就会得心应手。下面列了 logback 相对于 log4j 的一些优点：
    1、更快的实现  Logback 的内核重写了，在一些关键执行路径上性能提升10倍以上。而且logback 不仅性能提升了，初始化内存加载也更小了。
    2、非常充分的测试 Logback 经过了几年，数不清小时的测试。Logback 的测试完全不同级别的。在作者的观点，这是简单重要的原因选择 logback 而不是 log4j。
    3、Logback-classic 非常自然实现了 SLF4j Logback-classic 实现了 SLF4j。在使用SLF4j中，你都感觉不到 logback-classic。而且因为 logback-classic 非常自然地实现了SLF4J，所以切换到 log4j 或者其他，非常容易，只需要提供成另一个 jar 包就 OK，根本不需要去动那些通过 SLF4JAPI 实现的代码。
    4、非常充分的文档，官方网站有两百多页的文档。
    5、自动重新加载配置文件，当配置文件修改了，Logback-classic能自动重新加载配置文件。扫描过程快且安全，它并不需要另外创建一个扫描线程。这个技术充分保证了应用程序能跑得很欢在JEE环境里面。
    6、Lilith，Lilith 是 log 事件的观察者，和 log4j 的 chainsaw 类似。而 lilith 还能处理大数量的 log 数据 。
    7、谨慎的模式和非常友好的恢复，在谨慎模式下，多个 FileAppender 实例跑在多个 JVM 下，能够安全地写道同一个日志文件。RollingFileAppender 会有些限制。Logback 的FileAppender 和它的子类包括 RollingFileAppender 能够非常友好地从 I/O 异常中恢复。
    8、配置文件可以处理不同的情况，开发人员经常需要判断不同的 Logback 配置文件在不同的环境下（开发，测试，生产）。而这些配置文件仅仅只有一些很小的不同，可以通过,和来实现，这样一个配置文件就可以适应多个环境。
    9、Filters（过滤器），有些时候，需要诊断一个问题，需要打出日志。在log4j，只有降低日志级别，不过这样会打出大量的日志，会影响应用性能。在 Logback，你可以继续保持那个日志级别而除掉某种特殊情况，如 alice 这个用户登录，她的日志将打在 DEBUG 级别而其他用户可以继续打在WARN 级别。要实现这个功能只需加4行XML配置。可以参考 MDCFIlter 。    
   10、SiftingAppender（一个非常多功能的Appender）  它可以用来分割日志文件根据任何一个给定的运行参数。如，SiftingAppender 能够区别日志事件跟进用户的 Session，然后每个用户会有一个日志文件。
   11、自动压缩已经打出来的 log，RollingFileAppender 在产生新文件的时候，会自动压缩已经打出来的日志文件。压缩是个异步过程，所以甚至对于大的日志文件，在压缩过程中应用不会受任何影响。
   12、堆栈树带有包版本，Logback在打出堆栈树日志时，会带上包的数据。
   13、自动去除旧的日志文件，通过设置 TimeBasedRollingPolicy 或者SizeAndTimeBasedFNATP 的 maxHistory 属性，你可以控制已经产生日志文件的最大数量。如果设置 maxHistory 12，那那些 log 文件超过12个月的都会被自动移除。
总之，logback 比 log4j 太优秀了，让我们的应用全部建立 logback 上吧 ！


更快的执行速度： 基于我们先前在log4j上的工作，logback 重写了内部的实现，在某些特定的场景上面，甚至可以比之前的速度快上10倍。在保证logback的组件更加快速的同时，同时所需的内存更加少。
logback-classic 非常自然的实现了SLF4J：ogback-classic中的longging类自然的实现了SLF4J。当你使用 logback-classic作为底层实现时，涉及到LF4J日记系统的问题你完全不需要考虑。更进一步来说，由于 logback-classic强烈建议使用SLF4J作为客户端日记系统实现，如果需要切换到log4j或者其他，你只需要替换一个jar包即可，不需要去改变那些通过SLF4J API 实现的代码。这可以大大减少更换日记系统的工作量。
自动重新载入配置文件：Logback-classic可以在配置文件被修改后，自动重新载入。这个扫描过程很快，无资源争用，并且可以动态扩展支持在上百个线程之间每秒上百万个调用。它和应用服务器结合良好，并且在JEE环境通用，因为它不会调用创建一个单独的线程来做扫描。
优雅地从I/O错误中恢复：FileAppender和它的子类，包括RollingFileAppender，可以优雅的从I/O错误中恢复。所以，如果一个文件服务器临时宕机，你再也不需要重启你的应用，而日志功能就能正常工作。当文件服务器恢复工作，logback相关的appender就会透明地和快速的从上一个错误中恢复。
自动清除旧的日志归档文件：通过设置TimeBasedRollingPolicy 或者 SizeAndTimeBasedFNATP的 maxHistory 属性，你就可以控制日志归档文件的最大数量。如果你的回滚策略是每月回滚的，并且你希望保存一年的日志，那么只需简单的设置maxHistory属性为12。对于12个月之前的归档日志文件将被自动清除。
自动压缩归档日志文件：RollingFileAppender可以在回滚操作中，自动压缩归档日志文件。压缩通常是异步执行的，所以即使是很大的日志文件，你的应用都不会因此而被阻塞。
配置文件中的条件处理：开发者通常需要在不同的目标环境中变换logback的配置文件，例如开发环境，测试环境和生产环境。这些配置文件大体是一样的，除了某部分会有不同。为了避免重复，logback支持配置文件中的条件处理，只需使用,和，那么同一个配置文件就可以在不同的环境中使用了。
过滤： Logback拥有远比log4j更丰富的过滤能力。例如，让我们假设，有一个相当重要的商业应用部署在生产环境。考虑到大量的交易数据需要处理，记录级别被设置为WARN，那么只有警告和错误信息才会被记录。现在，想象一下，你在开发环境遇到了一个臭虫，但是在测试平台中却很难发现，因为一些环境之间(生产环境/测试环境)的未知差异。使用log4j，你只能选择在生产系统中降低记录的级别到DEBUG，来尝试发现问题。但是很不幸，这会生成大量的日志记录，让分析变得困难。更重要的是，多余的日志记录会影响到生产环境的性能。使用logback，你可以选择保留只所有用户的WARN级别的日志，而除了某个用户，例如Alice，而她就是问题的相关用户。当Alice登录系统，她就会以DEBUG级别被记录，而其他用户仍然是以WARN级别来记录日志。这个功能，可以通过在配置文件的XML中添加4行。请在相关章节中查找MDCFilter
logback原生支持同时按日期和文件大小分割日志，而log4j需要自己写代码实现
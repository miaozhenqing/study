package com.example.qzm.study.function.reactor.flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 参考 ：https://www.ibm.com/developerworks/cn/java/j-cn-with-reactor-response-encode/index.html?lnk=hmhm
 * @Version 1.0
 **/
public class FluxClient {
    public static void main(String[] args) {
        mergeFlux();

    }

    /**
     * 判断Flux中元素是否至少有1个满足Predicate条件
     */
    public static void anyFlux() {
        Flux<String> flux = Flux.just("hello", "world", "ted");
        Mono<Boolean> mono = flux.any(s -> s.contains("e"));
        mono.subscribe(System.out::println);
    }

    /**
     * 判断Flux中元素是否都满足Predicate条件
     */
    public static void allFlux() {
        Flux<String> flux = Flux.just("hello", "world", "ted");

        Mono<Boolean> mono = flux.all(s -> s.contains("e"));
        mono.subscribe(System.out::println);
    }

    /**
     * 将Flux中的元素提取为一个Map，Map的key根据Function生成
     */
    public static void collectMapFlux() {
        Flux<String> flux = Flux.just("hello", "world", "ted");
        Mono<Map<Character, String>> map = flux.collectMap(s -> s.charAt(0));
        map.subscribe(characterStringMap -> System.out.println(characterStringMap.get('t')));
    }

    /**
     * 将Flux中的元素收集到一个List中
     */
    public static void collectListFlux() {
        Flux<String> flux = Flux.just("hello", "world", "hi", "ted");
        Mono<List<String>> mono = flux.collectList();

        mono.subscribe(System.out::println);
    }

    /**
     * 将stream中的数据按照固定大小分配，新的Flux中的List的元素个数是2
     */
    public static void bufferFlux() {
        Flux<String> flux = Flux.just("hello", "world", "hi", "ted");
        Flux<List<String>> buffer = flux.buffer(3);

        buffer.subscribe(strings -> System.out.println(strings.size()));
    }

    /**
     * 根据Flux中的元素先生成Mono, 再对Mono中的元素进行map转换。
     */
    public static void flatMapFlux() {
        Flux<String> flux = Flux.just("hello world", "hi ted");

        Flux<String> flatMap = flux.flatMap(s -> Mono.just(s).map(s1 -> {
            String[] strings = s1.split("\\s");
            return new String(strings[0] + " - " + strings[1]);
        }));

        flatMap.subscribe(System.out::println);
    }

    /**
     * 接收Function
     */
    public static void mapFlux() {
        Flux<String> flux = Flux.just("hello", "world", "hi", "ted");

        Flux<String> skip = flux.map(s -> s + " is mapped");
        skip.subscribe(System.out::println);
    }

    /**
     * 去重
     */
    public static void distinctFlux() {
        Flux<String> flux = Flux.just("hello", "hello", "world", "hi", "ted");

        Flux<String> skip = flux.filter(s -> s.startsWith("h")).distinct();
        skip.subscribe(System.out::println);
    }

    /**
     * 过滤
     */
    public static void filterFlux() {
        Flux<String> flux = Flux.just("hello", "world", "hi", "ted");

        Flux<String> skip = flux.filter(s -> s.startsWith("h"));
        skip.subscribe(System.out::println);
    }

    /**
     * 只取前2个
     */
    public static void takeFlux() {
        Flux<String> flux = Flux.just("hello", "world", "hi", "ted");

        Flux<String> skip = flux.take(2);
        skip.subscribe(System.out::println);
    }

    /**
     * 略过2个
     */
    public static void skipFlux() {
        Flux<String> flux = Flux.just("hello", "world", "hi", "ted");

        Flux<String> skip = flux.skip(1);
        skip.subscribe(System.out::println);
    }

    /**
     * 结合为Tuple2元组类型
     */
    public static void zipFlux() {
        Flux<String> flux1 = Flux.just("hello", "world");
        Flux<String> flux2 = Flux.just("hi", "ted", "world2");
        Flux<Tuple2<String, String>> newSource = flux1.zipWith(flux2);
        newSource.subscribe(System.out::println);
    }

    /**
     * 合并
     */
    public static void mergeFlux() {
        Flux<String> source1 = Flux.just("hello", "world", "world");
        Flux<String> source2 = Flux.just("hi", "ted");

        Flux<String> merge = source1.mergeWith(source2);
        merge.subscribe(System.out::println);
    }

    /**
     * 创建Flux或Mono，调用subscribe()后，数据开始流动。
     * 主要方法有：just, fromArray, fromStream, fromIterable, range
     */
    public static void test1() {
        //just方法
        String[] arr = new String[]{"hello", "world"};
        Flux<String> flux1 = Flux.just(arr);
        flux1.subscribe(System.out::println);

        Mono<String> mono = Mono.just("hi world");
        mono.subscribe(System.out::println,(throwable)-> System.out.println(throwable),()-> System.out.println());

        //fromArray方法
        List<String> list = Arrays.asList("hello", "world");
        Flux<String> flux2 = Flux.fromIterable(list);

        //fromIterable方法
        List<String> fruitList = new ArrayList<>();
        fruitList.add("Apple");
        fruitList.add("Orange");
        fruitList.add("Grape");
        fruitList.add("Banana");
        fruitList.add("Strawberry");
        Flux<String> flux3 = Flux.fromIterable(fruitList);

        //fromStream方法
        Stream<String> stream = Stream.of("hi", "hello");
        Flux<String> flux4 = Flux.fromStream(stream);

        //range方法
        Flux<Integer> range = Flux.range(0, 5);

        //interval方法, take方法限制个数为5个
        //take(long n)，take(Duration timespan)和 takeMillis(long timespan)：按照指定的数量或时间间隔来提取。
        //takeLast(long n)：提取流中的最后 N 个元素。
        //takeUntil(Predicate<? super T> predicate)：提取元素直到 Predicate 返回 true。
        //takeWhile(Predicate<? super T> continuePredicate)： 当 Predicate 返回 true 时才进行提取。
        //takeUntilOther(Publisher<?> other)：提取元素直到另外一个流开始产生元素。
        Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1)).take(5);

    }


}

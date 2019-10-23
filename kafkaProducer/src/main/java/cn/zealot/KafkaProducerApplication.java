package cn.zealot;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * All right Reserved, Designed By ZHANGSEN
 *
 * @author : zhangsen
 * @date : 2019/10/15 9:41
 */
@Slf4j
public class KafkaProducerApplication {
    public static void main(String[] args) {
        log.debug("START");
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.34:9092,192.168.1.34:9093,192.168.1.34:9094");

        // ack是判别请求是否为完整的条件（就是是判断是不是成功发送了）。
        // 我们指定了“all”将会阻塞消息，这种设置性能最低，但是是最可靠的。
        props.put("acks", "all");

        // 如果请求失败，生产者会自动重试，我们指定是0次，如果启用重试，则会有重复消息的可能性
        props.put("retries", 0);

        // producer(生产者)缓存每个分区未发送的消息。
        // 缓存的大小是通过 batch.size 配置指定的。
        // 值较大的话将会产生更大的批。并需要更多的内存（因为每个“活跃”的分区都有1个缓冲区）。
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);

        // key.serializer和value.serializer示例，将用户提供的key和value对象ProducerRecord转换成字节
        // 你可以使用附带的ByteArraySerializaer或StringSerializer处理简单的string或byte类型。
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        // 发送的结果是一个RecordMetadata，它指定了消息发送的分区，分配的offset和消息的时间戳。
        // 如果topic使用的是CreateTime，则使用用户提供的时间戳或发送的时间（如果用户没有指定指定消息的时间戳）
        // 如果topic使用的是LogAppendTime，则追加消息时，时间戳是broker的本地时间。
        // Future<RecordMetadata> recordMetadataFuture = producer1.send(record);

        // 由于send调用是异步的，它将为分配消息的此消息的RecordMetadata返回一个Future。
        // 如果future调用get()，则将阻塞，直到相关请求完成并返回该消息的metadata，或抛出发送异常。
        // producer1.send(record).get();

        producer.send(new ProducerRecord<>("test-ZS", "key", "value"),
                (metadata, e) -> {
                    if (e != null) {
                        e.printStackTrace();
                    }
                    log.debug("The offset of the record we just sent is: " + metadata.offset());
                });


        // 发送到同一个分区的消息回调保证按一定的顺序执行，也就是说，在下面的例子中 callback1 保证执行 callback2 之前：

        producer.send(new ProducerRecord<>("test-ZS", "key1", "value1"),
                (metadata, e) -> {
                    if (e != null) {
                        e.printStackTrace();
                    }
                    log.debug("The offset of the record 1 we just sent is: " + metadata.offset());
                });
        producer.send(new ProducerRecord<>("test-ZS", "key2", "value2"),
                (metadata, e) -> {
                    if (e != null) {
                        e.printStackTrace();
                    }
                    log.debug("The offset of the record 2 we just sent is: " + metadata.offset());
                });

        producer.close();
    }
}

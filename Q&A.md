Here are the **most frequently asked Java interview questions** specifically targeting **Java 17 to Java 21 features**, along with modern core Java concepts that interviewers love to combine with new features. These are commonly asked in 2024–2025 for mid to senior-level positions (especially at FAANG, fintech, and product companies).

### Top Java 17–21 Specific Questions (Most Asked)

#### Java 17 (LTS) – Heavily Tested
1. What are **Sealed Classes**? Why were they introduced? Explain with a real-world example and the `permits` clause.

    Sealed classes in Java restrict which other classes can extend them, 
    allowing developers to define a controlled class hierarchy. 
    The permitted subclasses must be declared within the same module 
    or package and must be declared as final, sealed, or non-sealed

2. Explain **Pattern Matching for switch** (preview in 16/17, standard in 21). How is it different from traditional switch?

    Pattern matching for switch, a permanent feature since Java 21, enhances the switch construct by allowing case labels to use patterns (like types and record components) instead of only constant values. This allows the switch to perform complex, data-oriented logic that previously required cumbersome if-else if chains and explicit casting. 

    #### How Pattern Matching for switch Works
    Pattern matching in a switch statement/expression involves:
    * Selector Expression: The expression being evaluated can be of any reference type, expanding beyond the traditional integral primitives (excluding long), String, and enum types.
    * Case Labels with Patterns: Each case label can now contain a type pattern (e.g., case String s) or a record pattern.
    * Pattern Variables: If a pattern matches the input value, a pattern variable (e.g., s in case String s) is safely initialized and available for use within that case's scope, eliminating the need for explicit casting.
    * Guarded Patterns: You can add a when clause to a pattern case for extra conditional checks (e.g., case String s when s.length() > 5).
    * Exhaustiveness Checking: For switch expressions, the compiler ensures that all possible input values are covered, either by specific cases or a default case, which reduces the chance of runtime errors.
    * Null Handling: null can be handled explicitly as a case label (case null -> ...), regularizing null handling and avoiding a NullPointerException at runtime if not handled. 

    Pattern matching for switch offers a more powerful, concise, and safer way to handle complex data and types compared to the rigid, constant-based traditional switch.

3. What is **Pattern Matching for instanceof**? Show how it reduces boilerplate code.

    Pattern Matching for instanceof (a permanent feature since Java 16) streamlines the common programming scenario of checking an object's type and then explicitly casting it to that type to access its specific methods or fields. It combines the type check and the variable declaration/casting into a single, concise operation.

    #### How It Reduces Boilerplate Code
    Traditionally, working with instanceof required a two-step process involving a redundant variable declaration and explicit casting, which was verbose and could introduce errors.

    Before:
    ```java
    if (obj instanceof String) {
        String s = (String) obj; // Explicit casting and new variable declaration required
        System.out.println("Processing a String with length: " + s.length());
    } else {
        System.out.println("Processing a non-String object.");
    }
    ```

    After:
    ```java
    if (obj instanceof String s) { // Type check and variable declaration/casting in one line
        System.out.println("Processing a String with length: " + s.length());
    } else {
        System.out.println("Processing a non-String object.");
    }
    ```

4. What are **Records**? Are they immutable? Can they extend other classes? Can they be used in HashMap as keys?
    Records are transparent carriers for immutable data and are ideal for use as Data Transfer Objects (DTOs) or value types.


5. What happens if you try to modify a field in a record after creation?
   Not possible, records are immutable...

6. **Local Variable Type Inference (var)** – limitations in Java 17+ (e.g., var with lambdas, var in compound declarations).
    Can only be declared within methods, for loop or try catch statements.

7. What are **Helpful NullPointerExceptions**? Demonstrate with an example.
    common exception in Java that occurs when a program attempts to use an object reference that has the value null where an object is required.

    ```java
        BigInteger num = null;
        num.doubleValue();
    ```

8. Explain the new **Switch Expressions** (arrow syntax, yield) vs old switch statements.
- new switch expression evaluate to a single value
- uses the lambda operator syntax `->`
- value can be assigned variable and/or returned using yield within the case-block
- compiler returns an error if not all values are covered specially for enum and sealed types
- supports pattern matching for all reference types, including guarded patterns with a `when` clause

- legacy statements required control flow that directs execution
- uses `case` with `:` semicolon
- fall through the next case unless stopped by `break` statement
- limited to primitives, their wrappers and enum types


#### Java 21 (Latest LTS as of 2025) – Very Hot Right Now
9. **Pattern Matching for switch is now final** – Explain guarded patterns, when patterns, and null handling.
finalized in Java SE 21, allow patterns: `case Type t` and boolean logic via `when expression`;
and null handling: `case null -> "value is null";

if no `case null` is present, a NullPointerException is thrown when the expression evaluates to null, null case has to be explicitly handled.

10. **Record Patterns** – Deconstruct a record in one line. Nested record patterns example.
    ```java
    if (transac instanceof Transaction(Date date, String description, Double amount, TransactionType transactionType))
    ```
    Nested example:
    ```java
    if (transac instanceof Transaction(Payment(Date date, String description, Double amount), TransactionType transactionType))
    ```
    or
    ```java
    if (transac instanceof Transaction(Payment payment, TransactionType transactionType))
    ```

11. **Virtual Threads (Project Loom)** – This is the #1 trending question in 2025.
    - What are virtual threads? How do they differ from platform threads?
      lightweight threads managed by the JVM, not the OS, allowing millions of virtula threads to run concurrently, 
      drastically improving scalability for IO bound applications by mapping them to a small pool of OS "carrier" threads.
      Ideal for high concurrency servers, a permanent feature of `Java 21`

      Developers can write simple, sequential, blocking code (like Thread.sleep(), read()) for each task, and the JVM suspends the virtual thread, freeing the underlying OS thread for others.

    - What is `Thread.ofVirtual().start()`?
      creates a virtual thread, a lightweight object on the heap.

      * How They Work
        - Creation: A Thread.ofVirtual().start(...) call creates a virtual thread, a lightweight object on the heap.
        - Mounting: When code runs, the JVM mounts the virtual thread onto an available carrier (platform) thread.
        - Blocking I/O: When a blocking I/O operation occurs (e.g., database query, network call), the virtual thread is unmounted, and its state is saved,     freeing the carrier thread.
        - Resumption: When I/O completes, the JVM remounts the virtual thread onto any available carrier thread to resume execution. 

      * Benefits & Use Cases
        - Scalability: Handles massive numbers of concurrent connections (e.g., web servers) without resource exhaustion...
        - Simplicity: Eliminates the need for complex asynchronous/reactive programming for many high-concurrency tasks...
        - Performance: Reduces memory footprint and improves throughput for I/O-bound applications...
    
      * Availability
        - Introduced as preview features in Java 19 & 20.
        - Became a standard, permanent feature in Java 21

    - Are virtual threads suitable for CPU-bound work?

    - How do structured concurrency and `StructuredTaskScope` work? (Preview in 21)

12. **Sequenced Collections** (Java 21) – What are `SequencedCollection`, `SequencedSet`, `SequencedMap`? 
    What methods do they add (`getFirst()`, `getLast()`, `reversed()`)?

    Sequenced Collection, Set and Map are the new interfaces that provide a uniform API for collections with a defined encounter order.


#### Key Features and Methods
Sequenced collections offer a standard way to access and manipulate elements from either end and to process elements in reverse order. 

Method Signature 	Description
`void addFirst(E e)` Adds an element to the beginning of the collection.
`void addLast(E e)`	Adds an element to the end of the collection.
`E getFirst()`	Retrieves the first element.
`E getLast()`	Retrieves the last element.
`E removeFirst()`	Removes and returns the first element.
`E removeLast()`	Removes and returns the last element.
`SequencedCollection<E> reversed()`	Returns a reverse-ordered view of the collection.

    
    
13. **String Templates** (Preview in 21) – What are they? Why are they safer than concatenation?

    String templates were a preview feature in Java 21 and 22, designed to simplify string 
    formatting and interpolation, but they have since been withdrawn from the Java 
    Development Kit (JDK) for further redesign and are not available in Java 23 or later standard releases. 

    ```java
    String name = "Duke";
    String info = STR."My name is \{name}";
    System.out.println(info);
    ```

#### Java 19–20 Features (Sometimes Asked)
14. **Virtual Threads** were preview in 19 & 20 – differences between preview and final in 21.
Permanent feature in JDK 21 involved two main changes: guaranteed support for 
thread-local variables and enhanced monitoring for virtual threads created via 
the Thread.Builder API.

- Enhanced Monitoring for Virtual Threads 
The introduction of lightweight virtual threads in Java 21 via the Thread.Builder interface necessitates specific monitoring considerations: 
* Observability: Virtual threads are designed to be fully compatible with existing 
  debugging and profiling interfaces, reducing the need for new monitoring tools.
* Metrics: Tools and agents can monitor aggregate metrics, such as the total count 
  of running virtual threads, to understand resource utilization efficiently.
* Pinning Detection: Developers must be aware of "pinning," where a virtual thread 
  is temporarily tied to a carrier (platform) thread due to operations like synchronized 
  blocks or native methods. Monitoring tools help identify and mitigate pinning issues, which can hinder scalability. 

- When monitoring threads in a modern Java application, especially one using the Thread.Builder for virtual threads, crucial metrics include: 
* Thread Count: Total number of live threads (both platform and virtual).
* Thread State: Identifying BLOCKED, WAITING, TIMED_WAITING, or RUNNABLE states to pinpoint bottlenecks or deadlocks.
* CPU Usage: Monitoring which threads are consuming significant CPU time.
Contention Statistics: Data on lock contention helps optimize shared resource access. 


15. **Record Patterns** (preview in 19/20 → final in 21).

Record patterns (finalized in Java 21), allow you to deconstruct a record instance 
and extract its components into local variables.

16. **Foreign Function & Memory API** – Rarely deep, but asked in high-paying roles.

The Foreign Function & Memory (FFM) API, finalized in Java 22 (JEP 454) as part of Project Panama, 
enables Java programs to safely and efficiently interoperate with code and data outside the 
Java runtime. It serves as a modern, safer alternative to the complex and error-prone Java Native Interface (JNI). 

- Purpose and Benefits
The FFM API addresses the limitations of previous methods by: 
* Safety: Providing spatial and temporal memory safety, preventing common issues like memory leaks and dangling pointers associated with manual memory management in native code.
* Simplicity: Significantly reducing the boilerplate code required compared to JNI, offering a more Java-idiomatic approach to native integration.
* Performance: Minimizing the overhead of marshaling data between Java and native code, which can improve performance for memory-intensive operations.
* Portability: Offering a platform-independent way to access native libraries, as the API itself is part of the standard Java platform. 

- Key Components
The API is contained in the java.lang.foreign package and relies on several core abstractions: 
* MemorySegment: Represents a contiguous region of memory, which can be on the Java heap or off-heap (native memory). It provides safe, byte-accurate access operations.
* Arena: Manages the lifecycle of native memory segments, ensuring deterministic deallocation when the arena is closed, often used with a try-with-resources block.
* MemoryLayout: Describes the structure and organization of memory segments, similar to data structures in C, allowing for structured data access and manipulation.
* SymbolLookup: Used to find the address of a symbol (e.g., a function or global variable) within a loaded native library.
* Linker: Facilitates the linking process between Java and native functions, creating method handles for both calling native functions from Java (downcalls) and allowing native code to invoke Java code (upcalls).
* FunctionDescriptor: Models the signature of foreign functions, specifying the argument and return types to ensure type safety during linking. 

- Common Use Cases
* Native Library Integration: Calling functions from existing C/C++ libraries, such as those for image processing, machine learning, or game engines.
* High-Performance Computing: Managing large, off-heap datasets more efficiently than is possible with on-heap Java objects, avoiding garbage collection overhead.
* Interfacing with Legacy Systems: Providing a robust way to integrate with existing legacy codebases written in other languages.




### Core Java + Modern Java Combo Questions (Very Common)

17. How do **records** work with JPA/Hibernate? Why are they problematic as entities?

    Records are powerful for representing immutable data (Data Transfer Objects) 
    but fundamentally clash with the mutable, proxy-based design of JPA entities.

    Records can be used to model embeddable components (value types). 
    An Address record, for example, can be embedded within an Author entity

18. Can a **record** implement an interface? Have custom methods? Static methods?

    Yes, a `record` can implement interfaces, have custom instance methods, and include static methods.

19. How would you make a **sealed hierarchy** with records and enums?

    Clear Domain Modeling: They provide a clean and robust way to model real-world concepts with limited, well-defined alternatives.
    Encapsulated Control: The author of the sealed type maintains full control over which classes can extend the hierarchy, even if the sealed type is public. 
    
    The sealed type and its permitted subclasses must be in the same package or the same module.
    Records and enums are automatically final, so they cannot be declared sealed or non-sealed themselves. 

    ```java
    // The sealed interface
    public sealed interface Shape permits Circle, NoShape {}

    // A record (implicitly final) to represent a Circle with a radius and center
    public record Circle(double radius, double x, double y) implements Shape {}

    // An enum (implicitly final) to represent a shape that doesn't exist
    public enum NoShape implements Shape { }
    ```

20. Compare **immutability** in: POJO + final fields vs Record vs @Value (Lombok).

    - POJO + final fields
    	* Enforced manually via final fields and no setters.

    - Record
      * Inherently immutable by design; all fields are implicitly final

    - @Value (Lombok)
      * Generates immutable objects with private final fields and no setters.


21. **Virtual threads vs Reactive programming (WebFlux)** – When to use which?

  Spring WebFlux is a reactive, non-blocking web framework for Java that enables the creation of highly concurrent and scalable applications using the reactive programming paradigm. It is an alternative to the traditional Spring MVC framework within the Spring ecosystem. 

  The introduction of virtual threads in newer versions of Java may also reduce the need for reactive programming in certain blocking request-response scenarios, though the push-based reactive model still offers unique advantages.

  Choose WebFlux if you are building systems with extreme concurrency demands, real-time data streaming, or long-lived connections, and specifically require the backpressure mechanism to manage data flow. The performance benefits in these niche scenarios can be superior, but it comes with a steep learning curve and increased complexity.

  Choose Virtual Threads if you want to achieve high scalability with minimal code changes and cognitive overhead, especially when working with existing blocking libraries. They allow you to write simple, sequential code that scales effectively for most typical I/O-bound web applications.

22. Will virtual threads make Spring’s `@Async` or ExecutorService obsolete?

  No, virtual threads will not make Spring’s @Async or standard ExecutorService APIs obsolete. 
  Instead, they enhance how these mechanisms work by providing a more efficient underlying implementation for concurrent tasks.

  * With Virtual Threads (Spring Boot 3.2+): Spring has adopted virtual threads as the default for many asynchronous operations. 
  You can easily configure @Async to use an executor backed by virtual threads, allowing for highly scalable asynchronous operations without manual pool tuning.
  The Annotation Stays: The @Async annotation remains the convenient, declarative way to express "run this method asynchronously" in Spring; only the machinery underneath changes.

  * ExecutorService APIs are the interface for virtual threads

23. How does the garbage collector behave differently with millions of virtual threads?

    * More frequent, smaller collections (potentially): Due to the increased object churn in the young generation heap space.
    * Improved pause times: Less time is spent traversing extensive GC roots, especially beneficial for concurrent collectors.
    * Higher overall heap usage: The memory previously in native space moves into the managed Java heap, requiring careful heap sizing configurations.
    * Collector-specific performance trade-offs: ZGC generally performs very well with virtual threads due to its efficiency in managing large, dynamically changing heap spaces with minimal pauses. 

24. What synchronization issues can arise with virtual threads? (e.g., `synchronized` blocks pin carrier threads)

    While virtual threads significantly reduce the need for explicit synchronization by encouraging standard imperative programming, they do not eliminate synchronization issues entirely. They operate within the same Java memory model as platform threads, meaning traditional synchronization problems like deadlocks, race conditions, and livelocks can and will still occur.

    * Prefer ReentrantLock over synchronized if your critical section performs I/O or calls native code, to avoid pinning issues.
    * Use shared concurrency utilities (ConcurrentHashMap, AtomicInteger, etc.) rather than manually locking simple operations.
    * Avoid ThreadLocal for application-wide context where possible (use method arguments or modern context propagation libraries instead). 

25. Explain **reified generics** vs Java’s type erasure – common question with sealed classes/records.

    Java's current implementation uses type erasure, where generic type arguments are removed during compilation and thus are not available at runtime in most scenarios
    Arrays in Java are a notable exception, as they are reified types and enforce their component type at runtime.

    The `OpenJDK's Project Valhalla` is exploring potential future enhancements to Java's type system, which could include some form of reified generics, possibly through generic specialization for primitive types (e.g., `List<int>`)

26. How do you test code that uses virtual threads? Any special considerations?

    Testing code that uses virtual threads is very similar to testing traditional concurrent code, as the programming model is the same imperative style. However, the unique, lightweight nature of virtual threads introduces a few special considerations regarding setup, monitoring, and interaction with testing utilities. 


### Coding Questions Frequently Asked (Java 17+ style)

- Write a sealed hierarchy using records and pattern matching in switch.
- Deconstruct a nested record: `Person(Name(String first, String last), Address(String city))` using record patterns.
- Convert legacy `instanceof + cast` code to use pattern matching.
- Implement a method using `switch` expression with guarded patterns and `when`.
- Show how you would process 1 million HTTP calls using virtual threads vs traditional thread pool.

### Quick One-Liners Frequently Asked

| Question                                    | Expected Answer |
|---------------------------------------------|-----------------|
| Is Java still pass-by-value?                | Yes, even with records and virtual threads |
| Can records have mutable fields?            | Yes, but strongly discouraged |
| Can sealed classes be in different files?   | Only if in same module and explicitly permitted |
| Default access modifier for ` in `permits` clause? | If omitted, compiler auto-fills named classes in same file |

### Most Important Topics to Master (2025)

| Rank | Topic                          | Why It's Hot |
|------|--------------------------------|--------------|
| 1    | Virtual Threads + Structured Concurrency | Biggest Java change in 20 years |
| 2    | Pattern Matching (all forms)   | Shows deep understanding of modern Java |
| 3    | Records + Sealed Classes       | Standard in new codebases |
| 4    | Sequenced Collections         | New interfaces, easy question |
| 5    | Real-world trade-offs (e.g., virtual threads vs reactive) | Senior-level thinking |

Prepare these well, especially **virtual threads** and **pattern matching** — they appear in almost every Java 17+ interview in 2025.

Good luck — you're targeting exactly the right version range!

Deterministic Random Number Generator Benchmarks
====================
By Karl Lopker


Introduction
--------
Deterministic Random Number Generators (DRNGs) are important for a wide variety of applications. However, all languages implement non secure DRNGs as the default. Depending on the application this can lead to weak cryptography and thus information leakage. Typically the more cryptography a RNG provides the slower it is. This paper details a Java DRNG test harness and evaluates the speed of some popular DRNGs.

Test Harness
------------
The test harness is designed to easily add more DRNGs. To do so, simply make a new class in the drng package that extends the harness.DRNG class. If the new DRNG requires any libraries, add that dependency to the ivy.xml file.

You only need to implements two methods, seed and run. The seed method is called by the test harness for each DRNG. The seed is held constant so each test run creates identical random numbers. The run method actually runs the DRNG and is called millions of times during each test.

To keep the tests as simple as possible, the harness uses runtime reflection to find all harness.DRNG subclasses in the drng package. This provides the ability to extend the harness with many new DRNGs without having to change any other code.

For dependency management, the harness uses Apache Ivy. However, the only requirements for the harness is Java 1.6+ and ant. Running 'ant init' will download Ivy then use Ivy to download the other dependencies from a Maven repository.

### Test Harness Usage
	ant init
	ant drng-bechmark


Algorithms Tested
-----------------
14 algorithms were used for this test. They were picked because of their popularity and availability.

Java comes with 3 DRNGs; Random, SecureRandom, and DevRandom. Random is the class most people will use when making random numbers. This method is not secure and should not be used for crypto applications. RandomSecure is secure and used the SHA1 hash to generate its numbers. The DevRandom class is also secure. It gets its numbers from the Linux /dev/random file which in turn obtains entropy from system events. DevRandom is the closest to a true random generator in this test suite.

The other secure DRNGs used are derived from popular hash functions. The hash functions are turned into generators by using a seed as the initial digest then feeding the output back in.

For the non secure RNGs the MersenneTwister and Wells algorithms were picked. Twister is very popular and used as the default RNG in some languages (Python). Wells is also another popular choice and comes in different sizes depending on how large a number is needed.

### Algorithm List
Secure
- /dev/random
- java.security.SecureRandom
- MD2
- MD5
- SHA1
- SHA256
- SHA384
- SHA512

Not secure
- java.util.Random
- MersenneTwister
- Well512a
- Well1024a
- Well19937a
- Well44497a

Test Environment
-------
In order to maintain test repeatability, Amazon's AWS EC2 was used. The machine was commissioned thusly:

- Amazon t1.micro EC2
- Ubuntu 12.04.1 x86_64
- Java 1.6

Method
------
Each algorithm was seeded with 0 and told to generate 5,000,000 numbers. The Zero class generates 0 and is used as an upper limit for speed.

Results
-------
	100329 num/sec -> MD2
	141786 num/sec -> DevRandom
	197461 num/sec -> SHA512
	234212 num/sec -> SHA384
	577163 num/sec -> SHA256
	1330412 num/sec -> MD5
	1485626 num/sec -> SHA1
	2849227 num/sec -> JavaSecureRandom
	16926771 num/sec -> Well44497a
	21308819 num/sec -> Well1024a
	23368043 num/sec -> Well19937a
	25539027 num/sec -> Well512a
	29744472 num/sec -> JavaRandom
	32821975 num/sec -> Well1024aRNG
	37400062 num/sec -> MersTwister
	130965357 num/sec -> Zero

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/static/modules/gviz/1.0/chart.js"> {"dataSourceUrl":"//docs.google.com/spreadsheet/tq?key=0Ajn6jJwwzI6HdGNfWUNvLVV6Q2E0TlVMNW1DWlNobkE&transpose=1&headers=1&range=B1%3AC15&gid=0&pub=1","options":{"titleTextStyle":{"bold":true,"color":"#000","fontSize":16},"vAxes":[{"useFormatFromData":true,"minValue":null,"viewWindowMode":null,"viewWindow":null,"maxValue":null},{"useFormatFromData":true}],"title":"DRNG numbers/sec","booleanRole":"certainty","animation":{"duration":500},"legend":"right","hAxis":{"title":"Numbers per Second","useFormatFromData":true,"minValue":null,"viewWindow":{"min":null,"max":null},"maxValue":null},"tooltip":{},"isStacked":false,"width":823,"height":531},"state":{},"view":{"columns":[{"calc":"emptyString","type":"string","sourceColumn":0},0,1,2,3,4,5,6,7,8,9,10,11,12,13,14]},"chartType":"BarChart","chartName":"Chart 1"} </script>


Analysis
--------
As to be expected, the non secure RNGs are an order of magnitude faster than the secure ones. The fastest secure RNG (SecureRandom) produces over 2 million numbers per second verses the Twister's almost 40 million. While the difference in speed is considerable, there's the question of how many applications can actually use that many random numbers.

The implications of using non secure RNGs may not be clear until after something goes wrong. On the other hand using secure RNGs will just slow down the program, an issue that is easily seen and fixed. Therefore, if an application needs considerably less than 2 million numbers a secure RNG should always be used. Only when performance becomes a problem or there is no sensitive information should non secure RNGs be considered.

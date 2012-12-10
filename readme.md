Deterministic Random Number Generator Benchmarks
====================

Usage
-----
Make sure Java 6/7 and ant is installed then run:

	ant init
	ant drng-bechmark


Project Site
------------
[Write up](http://ninj0x.github.com/DRNG-Benchmark)
[Presentation](https://docs.google.com/presentation/d/1cg-aCoQeVn441XSkF3w3VoLq5eZ6ePW2riAjN4Ihd1Q/edit)

Abstract
--------
Deterministic Random Number Generators (DRNGs) are important for a wide variety of applications. However, some DRNGs are less cryptographically secure than others. Typically the more cryptography a RNG provides the slower it is. This paper explores various ways of generating random numbers and analyses application performance requirements to see if insecure DRNGs can be discarded in practice.

Tests will be done on a micro Amazon Web Services instance to provide repeatable results.

List of algorithms tested
-------------------------
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

Test environment
-------
- Amazon t1.micro EC2
- Ubuntu 12.04.1 x86_64
- Java 1.6.

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





Deterministic Random Number Generator Benchmarks
====================

Usage
-----
Make sure Java 6/7 and ant is installed then run:

	ant init
	ant drng-bechmark


Project Site
------------
http://ninj0x.github.com/DRNG-Benchmark

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

Results
-------
Test environment:
- Amazon t1.micro EC2
- Ubuntu 12.04.1 x86_64
- Java 1.6.

Each algorithm was seeded with 0 and told to generate 5,000,000 numbers. The Zero class just returns 0 and is used as an upper limit for speed.





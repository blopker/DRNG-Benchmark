Deterministic Random Number Generator Benchmarks
====================

Usage
-----
Make sure Java 7 is installed then run:

	ant drng-bechmark


Abstract
--------
Deterministic Random Number Generators (DRNGs) are important for a wide variety of applications. However, some DRNGs are less cryptographically secure than others. Typically the more cryptography a RNG provides the slower it is. This paper explores various ways of generating random numbers and analyses application performance requirements to see if insecure DRNGs can be discarded in practice.

To provide repeatable results, tests will be done on a micro Amazon Web Services instance.



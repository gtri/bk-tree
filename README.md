BK-tree
=======

A Java [BK-tree](http://en.wikipedia.org/wiki/BK-tree) library.

BK-trees offer a simple index of elements in a [metric space](http://en.wikipedia.org/wiki/Metric_space) that allows for searching the tree for elements within a certain distance of the search query with sub-linear efficiency.

For example, a BK-tree with string elements and a metric like the [Damerau–Levenshtein distance](http://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance) can serve as a [fuzzy search](http://en.wikipedia.org/wiki/Approximate_string_matching) index.

This project's release artifacts are available in the Maven
[Central Repository](http://search.maven.org/#search%7Cga%7C1%7Cedu.gatech.gtri.bk-tree).

```
<dependency>
  <groupId>edu.gatech.gtri.bk-tree</groupId>
  <artifactId>bk-tree</artifactId>
  <version>1.0</version>
</dependency>
```

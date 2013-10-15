BK-tree
=======

A Java [BK-tree](http://en.wikipedia.org/wiki/BK-tree) library.

BK-trees offer a simple index of elements in a [metric space](http://en.wikipedia.org/wiki/Metric_space) that allows for searching the tree for elements within a certain distance of the search query with sub-linear efficiency.

For example, a BK-tree with string elements and a metric like the [Damerau–Levenshtein distance](http://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance) can serve as a [fuzzy search](http://en.wikipedia.org/wiki/Approximate_string_matching) index.

## Example usage

```
import edu.gatech.gtri.bktree.*;
import edu.gatech.gtri.bktree.BkTreeSearcher.Match;
import java.util.Set;
```

```
// The Hamming distance is a simple metric that counts the number
// of positions on which the strings (of equal length) differ.
Metric<String> hammingDistance = new Metric<String>() {
    @Override
    public int distance(String x, String y) {

        if (x.length() != y.length())
            throw new IllegalArgumentException();

        int distance = 0;

        for (int i = 0; i < x.length(); i++)
            if (x.charAt(i) != y.charAt(i))
                distance++;

        return distance;
    }
};

MutableBkTree<String> bkTree = new MutableBkTree<>(hammingDistance);
bkTree.addAll("berets", "carrot", "egrets", "marmot", "packet", "pilots", "purist");

BkTreeSearcher<String> searcher = new BkTreeSearcher<>(bkTree);

Set<Match<? extends String>> matches = searcher.search("parrot", 2);

for (Match<? extends String> match : matches)
    System.out.println(String.format(
        "%s (distance %d)",
        match.getMatch(),
        match.getDistance()
    ));

// Output:
//   marmot (distance 2)
//   carrot (distance 1)
```

## Release artifacts

This project's release artifacts are available in the Maven
[Central Repository](http://search.maven.org/#search%7Cga%7C1%7Cedu.gatech.gtri.bk-tree).

```
<dependency>
  <groupId>edu.gatech.gtri.bk-tree</groupId>
  <artifactId>bk-tree</artifactId>
  <version>1.0</version>
</dependency>
```

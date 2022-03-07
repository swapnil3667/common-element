#Common Elements

###Thought Process and Approach

I have implemented the solution as described in the following steps:

1. Implement a **BloomFilter** which takes following params as input
 
    ```
    bloom-filter-length Length of the BloomFilter ByteArray
    
    bloom-filter-num-hash Number of Hash Function to be used
    
    bloom-filter-algorithm Algorithm for hash function example: SHA1, MD5 etc.
    ``` 
2. Using Scala FS2 library to read the file in chunks of size 4096 Integers
3. The first input file is parsed in the first go in function 
    
    ``
    def populateBloomFilter()
    ``
4. The second input file is parsed again in chunks of 4096 integers and then its filtered with the already populated BloomFilter.
5. Elements of second input file present in the bloom filter are saved to the output file.

    
###Run the Code

Following steps needs to be executed

####Build Jar
We don't need this step , as I have already attached a built jar in the zip file.
We need to build it again , only if we make some changes to code.

``sbt clean compile assembly``

####Build Docker File:

``
docker build -t apple .
``

####Run the Bash File 
I have created a bash file with required docker command and jar to run the code

``
bash run.sh
``

Arguments to the Jar and class file 
```
docker run --rm -v ${PWD}:/opt \
-it apple scala -classpath  target/scala-2.12/common-element-assembly-0.1.jar com.apple.CommonElementsDriver \
--first-file-path input/first_input_file.txt \
--second-file-path input/second_input_file.txt \
--output-file-path output/common_elements.txt \
--bloom-filter-length 400 \
--bloom-filter-num-hash 600 \
--bloom-filter-algorithm SHA1
```

####Input/Output Path

```
    Input folder : input/
                        first_input_file.txt
                        second_input_file.txt
    Output folder : output/common_elements.txt                                               
                        
```


####Asymptotical complexity of your solution

The solution runs in O(n) time, where n is the length of the larger file
It scans both the file one by one and hence , hence, length of larger file is the time complexity of the solution        

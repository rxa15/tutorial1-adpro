# Deployment
Link deployment: https://eshop-adpro-rxa15.koyeb.app/
# Module 1
<details>
<summary>Reflection 1</summary>

Previously, during Platform Based Programming course, I found it hard to create a web-based project. One of the reasons
to that might be my disliking towards programming. Well, my programming journey was hard, I faced many obstacles in learning
how to code. However, things started to change when I looked back to my midterms & finals project for PBP class. Maybe
if I'd spent more time on that, the result would be better. That's why I started doing this project few days before the due
date (a reflection to my past self for being a deadliner). 

Although creating edit and delete product features still felt like a big challenge, I found it enjoying to debug and retype
my code and so on when I create new features. I think it is because of I have more time from the due date, I spent more time 
adapting to the use of Springboot. The learning module & tutorial were such lifesavers. Moreover, the teaching assistants 
were very helpful too! They meant a lot for answering our questions on Discord so that we won't have to figure out all by ourselves.
Terima kasih banyak kakak-kakak asdos! All in all, I still feel the need to learn more and adapting to Springboot so that (hopefully)
one day I will be able to not to rely on tutorial and creating a project by myself!

Clean code principles & secure coding practices that I have implemented in this project are:
1. ### Meaningful Names

    I used simple and easy-to-understand names for variables, functions, and classes in this project.
2. ### Function

    I separated various tasks to multiple functions so that each function has their own purpose. I also used names that are
descriptive so that it is easier for me to determine what this function does.
3. ### Comments

    I USED TO MAKE COMMENTS ON ALMOST EVERY LINE OF MY CODE during Foundations of Programming 1 & 2 courses :)
Turns out it was a big mistake. Therefore, right now I only explain my thoughts on the code and only use comments when needed.
4. ### Objects and Data Structure

    Interfaces, OOP, Getter, and Setter are being used in this project.
5. ### Error Handling & Secure Coding

    I implemented `assertNull` and `assertNotNull` to check whether the object is null or not. However, I still returned null
in some places on my code but yeah that means I still have rooms for improvement right?!
</details>

<details>
<summary>Reflection 2</summary>

1. After writing the unit tests and passing all the tests, I feel genuinely proud of my own code (note: I used to have
difficulties in testing my code for a period of time). In my opinion, the amount of unit tests that we should create
in our class depends on many factors, such as code complexity. The more complex the code is, the more unit tests should
be created. We can use code coverage as a metric to measure percentage of code passed in the unit tests. However, a 100%
percent code coverage does not imply that there are no bugs or errors in the code. It does not guarantee that every test 
case that were made had already covered all functionalities of the code.
2. From what I have learned in this class, I strongly disagree to the creation of new Java class similar to prior functional
test suites that have been made. It will be redundant to write the same implementations over and over again, besides it will
also reduce the code efficiency and cleanliness. Therefore, I suggest to implement the logic into one test class so that 
there won't be any duplicates while keep ensuring the test cover for both features.
</details>

# Module 2
<details>
<summary>Reflection</summary>

### Code Quality Issues That I Fixed
Here are the list of code quality issues that were detected by PMD and how I fixed them:
1. **Unnecessary modifier `public` on method ... :the method is declared in an interface type**
The methods which were detected with this issue by PMD are methods in an Interface. However, an Interface
has `public` modifier by default. Therefore, there is no need for me to redeclare the modifier on the methods. 
To address this issue, I removed the `public` modifier from the methods.
2. **Unused import `org.springframework.web.bind.annotation.*`**
I did not fix this issue because the line was originated from the first version of this project (I followed the tutorial)
so I thought it was unnecessary to delete the code.
3. **Unused local variable `product`**
I used the variable on deleteProductPage method in ProductController.java. At first, the parameter for `service.deleteProductById`
was `productId`, then I changed it into `product.getProductId()` so that it used `product` variable.

### CI/CD Implementations in This Project
As I see it, I believe that I have already implemented CI/CD in my project. For Continuous Integration (CI), I have created workflows that
are executed automatically whenever there are push/pull request to any branches (you can see the implementations in `ci.yml`
to run test suites and `pmd.yml` & `scorecard.yml` to address security issues). As for Continuous Development (CD), I 
used `Koyeb` as a deployment platform (and it is connected with my Github too!)
so that I ensure the continous development for this project.
</details>
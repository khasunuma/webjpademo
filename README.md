# webjpademo

Java EE / Jakarta EE から Spring 陣営に寝返った裏切り者、いつか吊してやるから覚悟しな！

## とりあえずわかっていること
1. 2つのEntity: Customer (One側), Remarks (Many側) はそれぞれ個別に save できている。
2. Customer のフィールドを変更後、save または saveAndFlush で変更が反映される。ただし、Remarks へのリレーションシップについては変更は反映されない。
3. save は新規の場合 em#persist、既存の場合は em#merge を行っていると思われる。
4. saveAndFlush は save + flush の組み合わせだと思われるが、flush を行う必要があるかは不明。save だけでも通常のフィールドは更新される。
5. ひょっとしたら Repository 周りをスクラッチで実装すれば解決してしまうかもしれないが、要文献調査。

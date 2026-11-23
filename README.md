# Create: Bitterballen

**Is an addon for Create that adds Dutch snacks.**

![Create Bitterballen Logo](https://cdn.modrinth.com/data/16DuAG4k/images/3b80462cd11469598ca037d7b1625b5607a059d7.png)

## Content:

- Mechanical Fryer
- Frying Oil
- Crystallised Oil
- Stroopwafels
- Oliebollen
- Kroketten
- Bitterballen
- Frikandellen
- Fries
- Stamppot
- Sunflower Seeds
- Churros
- And More!

![Collection of some items and blocks in the mod](https://cdn.modrinth.com/data/16DuAG4k/images/5bf42e3604d0f48b7b6da85f020d3b1fed8ca830.png)

## FAQ:

### What are Bitterballen?

"Bitterballen are a Dutch meat-based snack, made by making a very thick stew thickened with roux and beef stock and generously loaded with meat"

### When version 1.xx?

We're currently only on 1.21.1, 1.20.1 and 1.19.2 (only 1.21.1 actively supported). No plans for downgraded versions.

### Why is the mod ID "create_bic_bit"?

This mod used to be called "Create: Bicycles and Bitterballen" but that's no longer the case because the bicycle part of the addon got scrapped.

## Dependencies:

- [Create](https://modrinth.com/mod/create)

## Recommended:

- [Jade](https://modrinth.com/mod/jade)
- [JEI](https://modrinth.com/mod/jei)
- [AppleSkin](https://modrinth.com/mod/appleskin)

This mod was made with Modified assets from the Create Mod under the MIT License.

<!-- Don't include below in MR/CF pages -->
## Compiling from source

Use `gradlew.bat` instead of `./gradlew` on windows.
1. `git clone https://github.com/electricsteve/Create-Bitterballen`
2. `./gradlew clean build`
3. The mod jar will be located in `build/libs/`

> [!NOTE]
> You can run `./gradlew runData` to generate data (under normal circumstances this is not needed, it was already done before pushing), but to make it usable, remove `src/generated/resources/assets/create_bic_bit/lang/en_us.json`
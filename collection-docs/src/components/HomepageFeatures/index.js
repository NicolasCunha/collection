import clsx from 'clsx';
import Heading from '@theme/Heading';
import styles from './styles.module.css';

const FeatureList = [
  {
    title: 'Track',
    Svg: require('@site/static/img/undraw_collection_camera.svg').default,
    description: (
      <>
        Track your cards, vinyls, books or games collections in a single place.
      </>
    ),
  },
  {
    title: 'Available On Demand - in multiple ways',
    Svg: require('@site/static/img/undraw_collection_online.svg').default,
    description: (
      <>
        Cloud Sync. Local files. Multiple formats. <b>You</b> decide.
      </>
    ),
  },
  {
    title: 'Insights',
    Svg: require('@site/static/img/undraw_collection_track.svg').default,
    description: (
      <>
        See your history and how your collections are evolving over time.
      </>
    ),
  },
];

function Feature({Svg, title, description}) {
  return (
    <div className={clsx('col col--4')}>
      <div className="text--center">
        <Svg className={styles.featureSvg} role="img" />
      </div>
      <div className="text--center padding-horiz--md">
        <Heading as="h3">{title}</Heading>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default function HomepageFeatures() {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList.map((props, idx) => (
            <Feature key={idx} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}
